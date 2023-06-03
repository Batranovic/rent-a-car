Vue.component("profile-modification", {
	data: function(){
		return{
			user: {id: null, name: null, surname: null, gender: null, birthday: null, username: null, password: null},
			id: null
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
            	<td><label>Date of birth: </label></td>
            	<td><input type="date" v-model="user.birthday" v-bind:style="birthdayColor"></td>
            </tr>
              <tr>
            	<td><label>Username: </label></td>
            	<td><input type="text" v-model="user.username" v-bind:style="usernameColor"></td>
            </tr>
             <tr>
            	<td><label>Password: </label></td>
            	<td><input type="text" v-model="user.password" v-bind:style="passwordColor"></td>
            </tr>
                <button v-on:click="modify()">Modify</button><br>
                <h5 v-bind:style="errorColor">{{errorMessage}}</h5>
            </form>
        </div>
	
	`,
	
	mounted(){
		this.id = this.$route.params.id;
		axios.get('rest/userPage/searchById/' + this.id)
		.then(response => {
			this.user = response.data 
		})
		
	}, 
	
	methods: {
		modify: function(){
			axios.put('rest/users/', this.user)
			.then(response => {
				router.push(`/userPage`);
			})
		}
	}
	
});