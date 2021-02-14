import http from "../http-common";
class ReceiptDataService {
    addReceipt(r) {
        return http.post("/receipt", r);
    }
    getAllReceipts() {
        return http.get("/receipt");
    }
    getAllReceiptsByPid(pid) {
        return http.get(`/receipt/pid=${pid}`);
    }
}
export default new ReceiptDataService();
