Vue.component("signup", {
	data: function(){
		return{
			user: {name: null, surname: null, gender: null, birthday: null, username: null, password: null},
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
		<div>
            <h2>Sign up page</h2>
            <form>
            <tr>
            	<td><label>Name: </label></td>
            	<td><input type="text" v-model="user.name" v-bind:style="nameColor"></td>
            </tr>
             <tr>
            	<td><label>Surname: </label></td>
            	<td><input type="text" v-model="user.surname" v-bind:style="surnameColor"></td>
            </tr>
              <tr>
            	<td><label>Gender: </label></td>
             <select v-model="user.gender" v-bind:style="genderColor">
                    <option value="M">male</option>
                    <option value="F">female</option>
                </select><br>
            </tr>
             <tr>
            	<td><label>Birthday: </label></td>
            	<td><input type="text" v-model="user.birthday" v-bind:style="birthdayColor"></td>
            </tr>
              <tr>
            	<td><label>Username: </label></td>
            	<td><input type="text" v-model="user.username" v-bind:style="usernameColor"></td>
            </tr>
             <tr>
            	<td><label>Password: </label></td>
            	<td><input type="text" v-model="user.password" v-bind:style="passwordColor"></td>
            </tr>
                <button v-on:click="signUp()">Sign up</button><br>
                <h5>{{errorMessage}}</h5>
            </form>
        </div>
	
	`,
	
	mounted(){
		
		
	}, 
	
	methods: {
		signUp: function(){
			event.preventDefault();
			if(!this.user.name){
				this.nameColor='background-color: red';
			}else{
				this.nameColor='';
			}
			if(!this.user.surname){
				this.surnameColor='background-color: red';
			}else{
				this.surnameColor='';
			}
			if(!this.user.gender){
				this.genderColor='background-color: red';
			}else{
				this.genderColor='';
			}
			if(!this.user.birthday){
				this.birthdayColor='background-color: red';
			}else{
				this.birthdayColor='';
			}
			if(!this.user.username){
				this.usernameColor='background-color: red';
			}else{
				this.usernameColor='';
			}
			if(!this.user.password){
				this.passwordColor='background-color: red';
			}else{
				this.passwordColor='';
			}
			
			if(!this.user.name || !this.user.surname || !this.user.gender || !this.user.birthday || !this.user.username || !this.user.password){
				this.errorMessage='All fields are neccessery!';
				this.errorColor = "color:red";
				return;
			}
			
			if(this.errorMessage){
				this.errorColor = "color:red";
			}
			axios.post('rest/users/', this.user)
			.then(response => {
				router.push(`/userPage`);
			})
		}
	}
	
});