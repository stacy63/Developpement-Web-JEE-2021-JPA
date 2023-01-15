package com.example.jpa;

import com.example.jpa.model.Task;
import com.example.jpa.repository.CategoryRepository;
import com.example.jpa.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		List<String> list = Arrays.asList(strings);
		//Si le paramètre install est détecté à l'exécution, la base sera créée sinon l'étape sera sautée.
		if (list.contains("install")) {

			jdbcTemplate.execute("DROP TABLE tasks IF EXISTS");
			jdbcTemplate.execute("DROP TABLE categories IF EXISTS");
			jdbcTemplate.execute(
					"CREATE TABLE categories ("+
							"category_id IDENTITY PRIMARY KEY," +
							"name VARCHAR(20) DEFAULT '' "+
							");" );
			log.info("categories TABLE CREATED");

			jdbcTemplate.update("INSERT INTO categories(name) values('todo'); ");
			jdbcTemplate.update("INSERT INTO categories(name) values('wip');  ");
			jdbcTemplate.update("INSERT INTO categories(name) values('done'); ");
			log.info("categories TABLE POPULATED");

			jdbcTemplate.execute(
					"CREATE TABLE tasks (" +
							"   task_id       IDENTITY PRIMARY KEY," +
							"   category      INTEGER NOT NULL," +
							"   content       VARCHAR(500) NOT NULL," +
							"   creation_date DATE DEFAULT TODAY(), " +
							"   end_date      DATE DEFAULT NULL, " +
							"   FOREIGN KEY(category) REFERENCES categories(category_id)"+
							");");
			log.info("tasks TABLE CREATED");

			jdbcTemplate.update("INSERT INTO tasks (category, content) values(3, 'finir le tp 1'); ");
			jdbcTemplate.update("INSERT INTO tasks (category, content) values(2, 'finir le tp 2'); ");
			jdbcTemplate.update("INSERT INTO tasks (category, content) values(1, 'finir le tp 3'); ");
			jdbcTemplate.update("INSERT INTO tasks (category, content) values(1, 'finir le tp 3'); ");
			log.info("tasks TABLE POPULATED");

		}

		List<String> categories;
		String sql = "select * from categories";
		categories = jdbcTemplate.query(sql,
				(rs, rowNum) ->
				{ return new String (rs.getString("name") );
				}
		);
		log.info(categories.toString());

		sql = "SELECT * FROM tasks";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map row : rows) {
			log.info(row.get("content").toString());
			log.info(categories.get((Integer)row.get("category")-1));
		}



		//Task task = new Task(categoryRepository.findById(1L).get() , "essai en cours");
		//taskRepository.save(task);


	}
}
