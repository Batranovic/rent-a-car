Vue.component("login", {
  data: function() {
    return {
      user: { username: null, password: null },
      errorMessage: "",
      errorColor: "",
      usernameColor: "",
      passwordColor: ""
    };
  },

  template: `
    <div class="container">
      <h1>
        Log in to your account
      </h1>
      <form class="form-table" @submit.prevent="logIn">
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
          <button type="submit">Log in</button><br>
          <h5 :style="errorColor">{{ errorMessage }}</h5>
        </div>
        <p>
          Don’t have an account yet? <a href="#" @click="registration">Sign up</a>
        </p>
      </form>
       <button  class="car-objects-button" v-on:click="viewCarObjects">CarObjects</button><br>
    </div>
  `,

  methods: {
    registration: function() {
      router.push("/signUp");
    },
    viewCarObjects: function(){
		router.push("/viewRentACarObject");
	},

    logIn: function() {
      axios.post("rest/users/loginUser", this.user)
        .then(response => {
          const result = response.data;
          
          if (!result) {
            this.errorMessage = "Invalid credentials";
            this.user.username = "";
            this.user.password = "";
          
          } else {
            router.push(`/userPage/${result.username}`);
          }
        })
        .catch(error => {
          console.error(error);
          this.errorMessage = "An error occurred during login.";
        });
    }
  }
});
