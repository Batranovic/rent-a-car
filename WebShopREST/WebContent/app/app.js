const SignUp = {template: "<signup></signup>"}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/', component: SignUp},
    ]
});


var app = new Vue({
    router,
    el: "#users"
});