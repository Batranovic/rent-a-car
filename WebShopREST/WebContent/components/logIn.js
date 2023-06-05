Vue.component("login", {
	data: function(){
		return{
			user: {username: null, password: null},
			errorMessage: '',
			errorColor:'',
			usernameColor: '',
			passwordColor: ''
		}
	},
	
	template: `
		<div class="container">
				<h1>
                  	Log in to your account
                </h1>
			<form class="form-table">
				<table>
					<tr>
						<td>Username</td>
						<td><input type="text" required v-model="user.username" id="username" name="username"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" required v-model="user.password" id="password" name="password"></td>
					</tr>
				</table>
				<br>
				<div class="form-row">
					<button v-on:click="logIn(user.username)">Log in</button><br>
					<h5 v-bind:style="errorColor">{{errorMessage}}</h5>
				</div>
				 <p>
                    Don’t have an account yet? <a href="#" v-on:click="registration()">Sign up</a>
                 </p>
			</form>
		</div>
	
	`,
	
	mounted(){
		
	}, 
	
	methods: {
		registration:function() {
			router.push(`/signUp`)
		},
		login: function(username) {
            event.preventDefault();

              axios.get('rest/users')
            		.then(response => {
              const users = response.data;
              let user = users.find(user => user.username === username);

              if (!user) {
                this.errorMessage = "There is no user with the entered username";
                this.user.username = "";
                this.user.password = "";
              } else if (user.password !== this.user.password) {
                this.errorMessage = "Invalid password";
                this.user.password = "";
              } else {
                router.push(`/userPage/` + username);
              }
    	})
		
	}
	
}});