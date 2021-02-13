import http from "../http-common";
class DataService {
    getAllPeople() {
        return http.get("/person");
    }
    getPersonById(id) {
        return http.get(`/person/id=${id}`);
    }
    addPerson(p) {
        return http.post("/person", p);
    }
}
export default new DataService();
