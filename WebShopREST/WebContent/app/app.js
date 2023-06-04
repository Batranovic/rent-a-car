const SignUp = {template: "<signup></signup>"}
const UserPage = {template: "<user-page></user-page>"}
const ProfileModification = {template: "<profile-modification></profile-modification>"}
const ViewRentACarObject = {template: "<viewRentACarObject></viewRentACarObject>"}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/', component: SignUp},
		{ path: '/userPage', component: SignUp},        
        { path: '/userPage/profileModification', component: ProfileModification},
        { path: '/viewRentACarObject', component: ViewRentACarObject}
    ]
});


var app = new Vue({
    router,
    el: "#users"
});