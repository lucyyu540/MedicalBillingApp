<template>
  <div class="submit-form">
    <div class="form-group">
        <label for="name">환자 검색</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="name"
          name="name"
        />
    </div>
    <button @click="getPeople" class="btn btn-success">검색</button>
    <div class="col-md-6">
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(p, index) in people"
          :key="index"
          @click="setPersonActive(p, index)"
        >
          {{ p.name }}
        </li>
      </ul>
        <div v-if="people.length>0"><button class="m-3 btn btn-sm btn-success" @click="selectPerson">선택</button></div>
    </div>
    <div class="col-md-6">
      <div v-if="currentPerson">
        <div><label><strong>아름:</strong></label> {{ currentPerson.name }}</div>
        <div><label><strong>성별:</strong></label> {{ currentPerson.sex }}</div>
        <div><label><strong>생년월일:</strong></label> {{ currentPerson.dob }}</div>
      </div>
    </div>        
  </div>
</template>

<script>
import personDS from "../service/PersonDataService";
export default {
  name: "name-search",
  data() {
    return {
      searchName: null,
      people: [],
      currentIndex : -1,
      currentPerson: null,
      selectedPerson: null
    };
  },
  methods: {
    getPeople() {
        personDS.getPeopleByName(this.name)
        .then(res => {
             this.people = res.data;
        })
        .catch(e => {
            console.log(e);
        })
    },
    setPersonActive(p, index) {
        this.currentPerson = p;
        this.currentIndex = index;
    },
    selectPerson() {
        this.currentPerson = this.person;
        this.personFound = true;
    },
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>