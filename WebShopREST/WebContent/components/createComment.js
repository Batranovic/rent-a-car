Vue.component("createComment", {
  data: function() {
    return {
     	comments: {text: null, grade: null, userId: null, rentACArObjectId: null}
    };
  },

  template: `
    <div class="container">
	 <form class="form-table">
            <tr>
            	<td>Comment:</td>
            	<td><input type="text"  v-model = "comments.text" style="width: 100%; height: 70px;" ></td>
            </tr>
            <br>
              <tr>
            	<td><label>Grade:</label></td>
            	 <select name="grade"  v-model = "comments.grade" style="width: 210px;">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select><br>
            </tr>
         
            <div class="form-row">
                <button v-on:click="leaveComment()">Submit</button><br>
             </div>
         </form>
         </div>
  `,
	mounted(){
		
		
	}, 
  methods: {
		leaveComment: function(){
			event.preventDefault();
			this.comments.userId = localStorage.getItem("loggedUserId");
			this.comments.rentACArObjectId = localStorage.getItem("commentRentACarObjectId");
			
			axios.post('rest/comments/', this.comments)
			    .then(response => {
					const a = response.data;
					 router.push(`/userPage/`+this.comments.userId );
					  window.alert('Comment created successfully!');
			    });
		}
  }
   
});
