angular.module("UserService",[])
	   .constant("hostURL", "http://localhost:8084/")
	   .factory("UserService", function($http,$q,hostURL) {
	   		
		   return{
				
				fetchAllUsers: function(){
					
					return $http.get(hostURL+"fetch_users")
								.then(
										function(response){
											return response.data;
										},
										function(errResponse) {
											console.error("Error while fetching users");
											return $q.reject(errResponse);
										}
								);
				},
				createUser: function(user) {
					
					return $http.post(hostURL+"create_user", user)
								.then(
										function(response) {
											return response.data;
										},
										function(errResponse) {
											console.error("Error while creating user");
											return $q.reject(errResponse);
										}
								);
					
				},
				updateUser: function(user) {
					
					return $http.put(hostURL+"update_user", user)
					//return $http.post(hostURL+"update_user", user)
								.then(
										function(response) {
											return response.data;
										},
										function(errResponse) {
											console.error("Error while updating user");
											return $q.reject(errResponse);
										}
								);
				},
				deleteUser: function(user) {
					
					return $http.delete(hostURL+"delete_user/"+user.id)
								.then(
										function(response) {
											return response.data;
										},
										function(errResponse) {
											console.error("Error while deleting user");
											$q.reject(errResponse);
										}
								);
				}
			};
			
	   });