angular.module("myApp")
	   .controller("AppController", function($scope,UserService) {
	   		
		    var self=this;
			self.user={id:null,username:"",address:"",email:""};
			self.users=[];
			
			self.fetchAllUsers= function() {
				UserService.fetchAllUsers()
						   .then(
								   function(d) {
									   self.users=d;
								   },
								   function(errResponse) {
									   console.error("Error while fetching the all users");
								   }
						   );
			};
			
			self.createUser= function(user) {
				UserService.createUser(user)
						   .then(
								   self.fetchAllUsers,
								   function(errResponse) {
									   console.error("Error while creating User");
								   }
						   );
			};
			
			self.updateUser= function(user) {
				UserService.updateUser(user)
				           .then(
				        		   self.fetchAllUsers,
				        		   function(errResponse) {
				        			   console.error("Error while updating User");
				        		   }
				           );
			};
			
			self.deleteUser= function(user) {
				UserService.deleteUser(user)
						   .then(
								   self.fetchAllUsers,
								   function(errResponse) {
									   console.error("Error while deleting User");
								   }
						   );
			};
			
			self.fetchAllUsers();
			
			self.submit= function() {
				
				if(self.user.id===null){
					console.log("Saving New User",self.user);
					self.createUser(self.user);
				}else{
					self.updateUser(self.user, self.user.id);
					console.log("User updated with id ",self.user.id);
				}
				
				self.reset();
			};
			
			self.edit= function(id){
				
				console.log("id to be edited ",id)
				for (var i = 0; i < self.users.length; i++) {
					if(self.users[i].id===id){
						self.user=angular.copy(self.users[i]);
						break;
					}
				}
			};
			
			self.remove= function(id) {
				
				console.log("id to be deleted ",id);
				//clean the form if the user to be deleted is shown there
				for (var i = 0; i < self.users.length; i++) {
					if(self.users[i].id===id){
						user=angular.copy(self.users[i]);
						self.deleteUser(user);
						break;
					}
				}
				//reseting the form and model value
				if(self.user.id===id)
					self.reset();
			};
			
			self.reset= function() {
				self.user={id:null,username:"",address:"",email:""};
				//reset Form
				$scope.myForm.$setPristine();
			};
			
	   });