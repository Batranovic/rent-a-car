Vue.component("signup", {
	data: function(){
		return{

			user: {name: null, surname: null, gender: null, birthday: null, username: null, password: null },

			nameColor : '',
			surnameColor: '',
			genderColor: '',
			birthdayColor: '',
			usernameColor : '',
			passwordColor : '',
			errorMessage: '',
			errorColor: '',
		}
	},
	
	template: `
		<div class="container">
            <h1>Sign up page</h1>
            <form class="form-table">
            <tr>
            	<td><label>Name: </label></td>
            	<td><input type="text" v-model="user.name" v-bind:style="nameColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Surname: </label></td>
            	<td><input type="text" v-model="user.surname" v-bind:style="surnameColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Gender: </label></td>
             <select name="genderSelect" v-model="user.gender" v-bind:style="genderColor" style="width: 150px;">
             		<option selected disabled hidden>Select gender</option>
                    <option value="M">male</option>
                    <option value="F">female</option>
                </select><br>
            </tr>
            <br>
             <tr>
            	<td><label>Date of birth: </label></td>
            	<td><input type="date" v-model="user.birthday" v-bind:style="birthdayColor"></td>
            </tr>
            <br>
              <tr>
            	<td><label>Username: </label></td>
            	<td><input type="text" v-model="user.username" v-bind:style="usernameColor"></td>
            </tr>
            <br>
             <tr>
            	<td><label>Password: </label></td>
            	<td><input type="password" v-model="user.password" v-bind:style="passwordColor"></td>
            </tr>
            <br>
            <div class="form-row">
                <button v-on:click="signUp(user.username)">Sign up</button><br>
                <h5 v-bind:style="errorColor">{{errorMessage}}</h5>
             </div>
            </form>
        </div>
	
	`,
	
	mounted(){
		
		
	}, 
	
	methods: {
		signUp: function(username){
			event.preventDefault();
			if(!this.user.name){
				this.nameColor='border-color: red';
			}else{
				this.nameColor='';
			}
			if(!this.user.surname){
				this.surnameColor='border-color: red';
			}else{
				this.surnameColor='';
			}
			if(!this.user.gender){
				this.genderColor='border-color: red';
			}else{
				this.genderColor='';
			}
			if(!this.user.birthday){
				this.birthdayColor='border-color: red';
			}else{
				this.birthdayColor='';
			}
			if(!this.user.username){
				this.usernameColor='border-color: red';

			}else{
				this.usernameColor='';
			}
			if(!this.user.password){
				this.passwordColor='border-color: red';
				return;
			}else{
				this.passwordColor='';
			}
			
			if(!this.user.name || !this.user.surname || !this.user.gender || !this.user.birthday || !this.user.username || !this.user.password){
				this.errorMessage='All fields are neccessary!';
				this.errorColor = "color:red";
				return;
			}
			
			this.errorMessage = '';
		
			axios.post('rest/users/createUser', this.user)
			    .then(response => {
					const a = response.data;
			        router.push(`/`);
			    });
		}
	}
	
});