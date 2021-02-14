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
            path: "/invoice/add",
            name: "invoice-add",
            component: () => import("./components/AddInvoice")
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
        },
        {
            path: "/search-person",
            name: "search-person",
            component: () => import("./components/SearchPerson")
        }

    ]
});