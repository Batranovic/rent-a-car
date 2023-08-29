const LogIn = {template: "<login></login>"}
const SignUp = {template: "<signup></signup>"}
const UserPage = {template: "<user-page></user-page>"}
const ProfileModification = {template: "<profile-modification></profile-modification>"}
const ViewRentACarObject = {template: "<viewRentACarObject></viewRentACarObject>"}
const CreateObject = {template: "<createObject></createObject>"}
const CreateVehicle = {template: "<createVehicle></createVehicle>"}
const CreateManagerForObject = {template: "<managerForObject></managerForObject>"}
const DetailedObject = {template: "<detailedObject></detailedObject>"}
const ManagerPage = {template: "<managerPage></managerPage>"}
const AdminPage = {template: "<adminPage></adminPage>"}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/logIn', component: LogIn},
        { path: '/signUp', component: SignUp},
		{ path: '/userPage/:username', component: UserPage},         
        { path: '/searchByUsername/:username', component: ProfileModification},
        { path: '/', component: ViewRentACarObject},
        { path: '/createObject', component: CreateObject},
        { path: '/createVehicle', component: CreateVehicle},
        { path: '/managerForObject', component: CreateManagerForObject},
        { path: '/detailedObject/:id', name: 'detailed', component: DetailedObject},
        { path: '/managerPage/:username', component: ManagerPage},
        { path: '/adminPage/:username', component: AdminPage}
    ]
});


var app = new Vue({
    router,
    el: "#users"
});