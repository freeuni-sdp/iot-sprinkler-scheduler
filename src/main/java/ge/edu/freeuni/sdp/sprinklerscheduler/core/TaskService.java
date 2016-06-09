package ge.edu.freeuni.sdp.sprinklerscheduler.core;

import javax.ws.rs.*;


import com.microsoft.azure.storage.StorageException;

@Path("sprinklerscheduler")
public class TaskService {

	/*
	HTTP  |              /todos   	            	|            /todos/{ID}
	------|-----------------------------------------|----------------------------------------
	GET   | 200 (OK), list of tasks. 				| 200 (OK), single task. 404 (Not Found).
	PUT   | 404 (Not Found), N/A 					| 200 (OK). 404 (Not Found).
	POST  | 201 (Created),  Location: /todos/{ID} 	| 404 (Not Found).
	DELETE| 404 (Not Found) 						| 200 (OK). 404 (Not Found)
    */	

	

	@GET
	public String read() throws StorageException {
		return "scheduler hello";
	}

}