import http from "../http-common";
class PersonDataService {
    addPerson(p) {
        return http.post("/person", p);
    }
    getAllPeople() {
        return http.get("/person");
    }
    getPersonById(id) {
        return http.get(`/person/id=${id}`);
    }
    getPeopleByName(name) {
        return http.get(`/person/name=${name}`);
    }
    updatePersonById(id, p) {
        return http.put(`/${id}`, p);
    }
    deletePersonById(id) {
        return http.delete(`/${id}`);
    }
}
export default new PersonDataService();
