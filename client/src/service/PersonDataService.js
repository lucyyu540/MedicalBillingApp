import http from "../http-common";
class PersonDataService {
    getAllPeople() {
        return http.get("/person");
    }
    getPersonById(id) {
        return http.get(`/person/id=${id}`);
    }
    getPersonByName(name) {
        return http.get(`/person/name=${name}`);
    }
    addPerson(p) {
        return http.post("/person", p);
    }
    updatePersonById(id, p) {
        return http.put(`/${id}`, p);
    }
    deletePersonById(id) {
        return http.delete(`/${id}`);
    }
}
export default new PersonDataService();
