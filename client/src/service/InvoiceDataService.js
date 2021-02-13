import http from "../http-common";
class InvoiceDataService {
    addInvoice(i) {
        return http.post("/invoice", i);
    }
    getAllInvoices() {
        return http.get("/invoice");
    }
    getAllUnclearedInvoices() {
        return http.get(`/invoice/uncleared`);
    }
    getAllUnclearedInvoicesByPid(pid) {
        return http.get(`/invoice/uncleared/pid=${pid}`);
    }
    getInvoiceById(id) {
        return http.get(`/invoice/id=${id}`);
    }
}
export default new InvoiceDataService();
