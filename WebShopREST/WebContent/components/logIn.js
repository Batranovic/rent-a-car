Vue.component("login", {
	data: function(){
		return{
			user: {username: null, password: null},
			errorMessage: '',
			errorColor:''
		}
	},
	
	template: `
		<div>
			<form>
				<h1>
                  	Log in to your account
                </h1>
				<table>
					<tr>
						<td>Username</td>
						<td><input type="text" v-model="user.username"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" v-model="user.password"></td>
					</tr>
				</table>
				<button v-on:click="logIn(user.username)">Log in</button><br>
				<h5 v-bind:style="errorColor">{{errorMessage}}</h5>
				
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
		}
		
	}
	
});