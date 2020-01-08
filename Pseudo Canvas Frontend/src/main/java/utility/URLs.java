package utility;

public class URLs {

	/*
	 * Basic documentation and standardization of formatting
	 */
	
	//public static final String baseURL = "http://cs309-ad-2.misc.iastate.edu:8080";
	public static final String baseURL = "http://localhost:8080";
	
	// noah@iastate.edu ABCabc123!
	// Main ---> (Login / newUser) ---> Dashboard <---> (Assignments / ClassList / MessageBoard / NotificationWall / Settings / Groups / School Info)
	// highest number: 71
	
	
	
	
	
	// Admin Controller
	/*
	 * add class info: 						/1/{email}/{className}/{days}/{time}/{location}/{roomNum}
	 */
	
	
	
	// Student Controller
	/*
	 * check messages:						/5/{email}
	 * delete messages:						/6/{email}/{messages}
	 */
	
	
	
	// Teacher Controller
	/*
	 * get students in a class:				/7/{className}
	 * get assignment by className:			/56/{className}
	 * create a class:						/8/{email}/{className}
	 * create a section:						/9/{className}
	 * add / change class desc:				/13/{email}/{classId}/{description}
	 * add a grade:							/14/{grade}/{email}/{teacherEmail}/{assignment}/{className}
	 * edit a grade:							/55/{email}/{teacherEmail}/{assignment}/{className}/{score}
	 * post a new ass to a class:			/57/{className}/{assignmentName}
	 */
	
	
	
	// User F Controller
	/*
	 * get all users in a class:				/15/{classId}
	 * user sign in:							/17/{email}/{password}
	 * get class info:						/18/{className}
	 * get users grades:						/22/{email}
	 * get all messages:						/26/{email}
	 * add a class:							/23/{email}/{className}
	 * remove a class:						/24/{email}/{className}
	 * send a message:						/25/{email}/{senderEmail}/{txt}
	 * post create group chat:				/61/{email}
	 * post enter a group chat:				/62/{email}/{groupNum}
	 * post send msg to gc:					/63/{email}/{msg}
	 * post send a notification:				/66/{email}/{senderEmail}/{msg}
	 */
	
	
	
	// Class Controller
	/*
	 * get all class objects:				/32
	 * get a class:							/33/{id}
	 * get a class:							/34/{name}
	 * create new class object:				/35/{name}
	 * delete a class object:				/36/{id}
	 * delete all class objects:				/37
	 */
	
	
	
	// Grade Controller
	/*
	 * get all grades:						/38
	 * get a users grades:					/39/{email}
	 * get a classes grades:					/58/{className}
	 * get a classes grades for an ass:		/59/{className}/{assName}
	 * get a grading group:					/71/{gNum}
	 * post a grade for a group:				/60/{assName}/{groupNum}/{grade}
	 * post create a grading group:			/64/{emailA}/{emailB}/{emailC}
	 * post curve an assignment:				/65/{className}/{assName}/{curve}
	 */
	
	
	
	// School Controller
	/*
	 * get all schools:						/40
	 * get school:							/41/{id}
	 * get school:							/42/{name}
	 * create a school object:				/43/{name}
	 * delete a school object:				/44/{id}
	 * delete all school objects:			/45	
	 * get students at school:				/67/{schoolName}
	 * get teachers at school:				/68/{schoolName}
	 * get ta's at school:					/69/{schoolName}
	 * get every assignment at school:		/70/{schoolName}
	 */
	
	
	
	// User Controller
	/*
	 * get all users:						/46
	 * get user:								/47/{id}
	 * get user:								/48/{name}
	 * get user:								/49/{email}
	 * 
	 * create user object:					/50/{email}/{password}/{type}
	 * create user object:					/54/{email}/{password}/{name}/{type}/{school}
	 * delete user object:					/51/{id}
	 * delete all user objects:				/52
	 * change type of user:					/53/{email}/{type}
	 */
	
	
	
	// Main Controller
	/*
	 * reset database:						/reset
	 * clear database:						/clear
	 * generate grades:						/grades
	 */
	
	
	
	// Update Controller
	/*
	 * update user:							/update/user/{id}/{email}/{password}/{name}/{type}/{pri}
	 * update class:							/update/class/{id}/{name}/{section}/{description}/{info}/{school}
	 * update school:						/update/school/{id}/{name}
	 * update grade:							/update/grade/{id}/{assignment}/{grade}/{userEmail}/{className}
	 * update message:						/update/message/{id}/{msg}/{date}/{sent}/{notification}/{userEmail}
	 */
}
