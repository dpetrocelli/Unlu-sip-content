package spring.examples.controllers;
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
import java.util.concurrent.ThreadLocalRandom;
import com.google.gson.Gson;

import spring.examples.model.Task;
import spring.examples.services.CmdRunner;

@RestController
@RequestMapping(value = "/api/taskmanager")
public class TasksController {
	Gson gsonManager = new Gson();
	int min = 8000;
	int max = 9999;
	
	// @Autowired
	// RabbitMQSender rabbitMQSender;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/listTasks/{clientId}")
	public String listTasks(@PathVariable Long clientId){
		log.info ("Client requested List Tasks method for client: "+ clientId);
		return "Message: "+ clientId +" has been sent to the RabbitMQ Java InUse Successfully";
    }

	@GetMapping("getTask/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id){
        log.info ("REST CONTROLLER: Client requested taskID: "+id);

        // if(!lugarServicio.existePorId(id))
        //     return new ResponseEntity(new Mensaje("no existe ese lugar"), HttpStatus.NOT_FOUND);
        // Lugar Lugar = lugarServicio.obtenerPorId(id).get();
        // return new ResponseEntity<Lugar>(Lugar, HttpStatus.OK);
		log.info ("REST CONTROLLER: returned task ?");
		return new ResponseEntity<Task>(new Task(), HttpStatus.OK);
    }

	@PostMapping("/createTask")
    //public ResponseEntity<?> create (@RequestBody Task task){
	public ResponseEntity<?> create (@RequestBody String task){
		
        try {
			CmdRunner cmdRunner = new CmdRunner();
			// [STEP 0] - Receive Task request and convert into Java Object
			Task t = gsonManager.fromJson(task, Task.class);
			t.getFullContainerImage()
			log.info ("TASK: "+t.getTaskName()+ " received ;)");
			Boolean port_available = false;
			int randomNum;
			while (!port_available) {
				randomNum = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
				
				String result = cmdRunner.runCommand("/tmp/", "ss -tulpn | grep :"+randomNum+" | head -n1");
				log.info("result: "+result);
				if (result.length() > 0){
					log.error("Port "+randomNum+" used, retrying");	
				}else{
					port_available = true;
					log.info ("Port "+randomNum+" free, let's start docker container");	
				}
				Thread.sleep(2000);
			}
			
			String docker_container = "docker run --rm --name="+t.getTaskName()+"-"+randomNum+" -p "+randomNum+":8080 "+ t.getFullContainerImage();
			
			cmdRunner.runCommand("/tmp/", docker_container);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		

		// [STEP 1] - Once task loaded, launch Docker container
		// [STEP 1.1] - Check available port
		
		

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
		
		
		// log.info (this.gsonManager.toJson(t));
		// log.info("REST CONTROLLER: Task to be created: "+task.toString());
		// try {
		// 	CmdRunner cmdRunner = new CmdRunner();
		// 	cmdRunner.runCommand("/tmp/", "docker ps -a");
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

        return new ResponseEntity<String>("Task stored ", HttpStatus.CREATED);
    }
}
// package com.backend.controladores;
// import com.backend.dto.Mensaje;
// import com.backend.entidades_modelo.*;
// import com.backend.servicios.LugarServicio;
// import com.google.gson.Gson;
// import org.apache.commons.lang3.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping("/api/lugar/")
// public class LugarControlador {
    
//     private final Logger log = LoggerFactory.getLogger(this.getClass());
//     @Autowired
//     LugarServicio lugarServicio;


//     @GetMapping("listartodos")
//     public ResponseEntity<List<Lugar>> getLista(){
//         List<Lugar> lista = lugarServicio.obtenerTodos();
//         log.info(" Obteniendo lista de Lugares");
//         return new ResponseEntity<List<Lugar>>(lista, HttpStatus.OK);
//     }


//     @GetMapping("detalle/{id}")
//     public ResponseEntity<Lugar> getOne(@PathVariable Long id){
//         log.info(" Obteniendo detalle de Lugar: "+id);

//         if(!lugarServicio.existePorId(id))
//             return new ResponseEntity(new Mensaje("no existe ese lugar"), HttpStatus.NOT_FOUND);
//         Lugar Lugar = lugarServicio.obtenerPorId(id).get();
//         return new ResponseEntity<Lugar>(Lugar, HttpStatus.OK);
//     }

//     @PostMapping("nuevo")
//     public ResponseEntity<?> create (@RequestBody String lug){

//         Lugar lugar = new Gson().fromJson(lug, Lugar.class);
//         log.info(" Insertando nuevo Lugar: "+lugar.getNombre());
//         if(StringUtils.isBlank(lugar.getNombre()))
//             return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//         if(lugarServicio.existePorNombre(lugar.getNombre()))
//             return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

//         lugarServicio.guardar(lugar);
//         return new ResponseEntity(new Mensaje("Lugar guardado"), HttpStatus.CREATED);
//     }


//     @PutMapping("actualizar/{id}")
//     public ResponseEntity<?> update(@RequestBody Lugar Lugar, @PathVariable("id") Long id){
//         log.info(" Actualizar Lugar: "+id);
//         if(!lugarServicio.existePorId(id))
//             return new ResponseEntity(new Mensaje("no existe el Lugar "+lugarServicio.obtenerPorId(id)), HttpStatus.NOT_FOUND);
//         if(StringUtils.isBlank(Lugar.getNombre()))
//             return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);



//         Lugar baseLugar = lugarServicio.obtenerPorId(id).get();
//         baseLugar.setDescripcion(Lugar.getDescripcion());
//         baseLugar.setNombre(Lugar.getNombre());

//         lugarServicio.guardar(baseLugar);
//         return new ResponseEntity(new Mensaje("Lugar actualizado"), HttpStatus.CREATED);
//     }

//     @DeleteMapping("borrar/{id}")
//     public ResponseEntity<?> delete(@PathVariable Long id){
//         log.info(" Borrando Lugar: "+id);
//         if(!lugarServicio.existePorId(id))
//             return new ResponseEntity(new Mensaje("no existe ese Lugar"), HttpStatus.NOT_FOUND);
//         lugarServicio.borrar(id);
//         return new ResponseEntity(new Mensaje("Lugar eliminado"), HttpStatus.OK);
//     }


// }
