Vue.component("user-page", { 
	data: function () {
	    users: null
	    passedUsername: null
	},
	    template: 
	    `
	    <div>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Gender</th>
                    <th>Date of birth</th>
                    <th>Username</th>
                </tr>
                <tr v-for="u in users">
                    <td>{{u.name}}</td>
                    <td>{{u.surname}}</td>
                    <td>{{u.gender}}</td>
                    <td>{{u.birthday}}</td>
                    <td>{{u.username}}</td>
                    <td><button v-on:click="modify(p.id)">Modify</button></td>
                </tr>
            </table>
        </div>
	    `,
    mounted () {
		this.passedUsername = this.$route.params.username;
        axios.get('rest/users/searchByUsername/')
		.then(response => {
			this.users = response.data
		});
    },
    methods: {
    	modify : function(username) {
			router.push(`/editUserProfile` + username);
    	}
    }
});