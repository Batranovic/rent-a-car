Vue.component("user-page", { 
	data: function () {
		return{
		    users: null,
		    passedUsername: null
	}
	},
	    template: 
	    `
	   <div class="container">
		<form v-if="users">
			<h3><b>Your page</b></h3>
				<table>
					<tr>
						<td>Name:</td>
						<td><input type = "text" v-model = "users.name" ></td>
					</tr>
					<tr>
						<td>Surname:</td>
						<td><input type = "text" v-model = "users.surname" ></td>
					</tr>
					<tr>
						<td>Username:</td>
						<td><input type = "text" v-model = "users.username"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type = "text" v-model = "users.password"></td>
					</tr>
					<tr>
						<td>Gender:</td>
						<td><input type = "text" v-model = "users.gender"></td>
					</tr>
					<tr>
						<td>Birthday:</td>
						<td><input type = "date" v-model="formattedBirthday"></td>
					</tr>
					<button type="sumbit" v-on:click="modify()">Modify</button>
				</table>
			</form>
        </div>
	    `,
	 computed: {
	    formattedBirthday: {
	      get() {
	        if (this.users && this.users.birthday) {
	          const parts = this.users.birthday.split("-");
	          if (parts.length === 3) {
	            return `${parts[2]}-${parts[1]}-${parts[0]}`;
	          }
	        }
	        return "";
	      },
	      set(value) {
	        const parts = value.split("-");
	        if (parts.length === 3) {
	          this.users.birthday = `${parts[2]}-${parts[1]}-${parts[0]}`;
	        }
	      }
	    }
	  },
    mounted () {
		this.passedUsername = this.$route.params.username;
        axios.get(`rest/users/searchByUsername/`+ this.passedUsername)
		.then(response => {
			this.users = response.data
		});
    },
    methods: {
	   modify: function () {
	    axios.put(`rest/users/update/${this.users.id}`, this.users) 
	      .then(response => {
	        console.log('Modified:', response.data);
	      })
	      .catch(error => {
	        console.error("Error updating user:", error);
	      });
	  }
    }
});