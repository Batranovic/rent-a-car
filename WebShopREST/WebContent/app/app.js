const SignUp = {template: "<signup></signup>"}
const ViewRentACarObject = {template: "<viewRentACarObject></viewRentACarObject>"}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/', component: SignUp},
        { path: '/viewRentACarObject', component: ViewRentACarObject}
    ]
});


var app = new Vue({
    router,
    el: "#users"
});