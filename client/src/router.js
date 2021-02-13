import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);
export default new Router ({
    mode: "history",
    routes: [
        {
            path: "/person/add",
            //alias: "/add-person",
            name: "person-add",
            component: () => import("./components/AddPerson")
        },
        {
            path: "/invoice",
            name: "invoice",
            component: () => import("./components/Invoice")
        },
        {
            path: "/receipt/add",
            name: "receipt-add",
            component: () => import("./components/AddReceipt")
        },
        {
            path: "/receipt",
            name: "receipt",
            component: () => import("./components/Receipt")
        }

    ]
});