import http from "../http-common";
class RefundDataService {
    addRefund(r) {
        return http.post("/refund", r);
    }
    getAllRefunds() {
        return http.get("/refund");
    }
}
export default new RefundDataService();