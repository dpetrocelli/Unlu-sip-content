
package task.demo.controllers;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import task.demo.model.Task;


@RestController
@RequestMapping(value = "/api/task/example")
public class TasksController {
	Gson gsonManager = new Gson();
	// @Autowired
	// RabbitMQSender rabbitMQSender;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@PostMapping("/runTask")
	public ResponseEntity<?> create (@RequestBody String task){
		
        // [STEP 0] - Receive Task request and convert into Java Object
		Task t = gsonManager.fromJson(task, Task.class);
		int result = t.addMethod();
		log.info ("remote job result: "+result);
		return result;
		// [STEP 1] - Once task loaded, launch Docker container

		// log.info (this.gsonManager.toJson(t));
		// log.info("REST CONTROLLER: Task to be created: "+task.toString());
		// try {
		// 	CmdRunner cmdRunner = new CmdRunner();
		// 	cmdRunner.runCommand("/tmp/", "docker ps -a");
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

        return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
    }
}
