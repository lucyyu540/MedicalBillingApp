import http from "../http-common";
class RefundDataService {
    addRefund(r) {
        return http.post("/refund", r);
    }
    getAllRefunds() {
        return http.get("/refund");
    }
    cancelRefund(id, amount) {
        return http.put(`/refund/cancel/id=${id}/amount=${amount}`)
    }
}
export default new RefundDataService();