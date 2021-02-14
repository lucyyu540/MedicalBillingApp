<template>
  <div>
    <label for="searchName">환자 검색</label>
    <div class="input-group mb-3 submit-form">
        <input type="text" 
            class="form-control" 
            placeholder="이름" 
            id="searchName"
            required
            v-model="searchName"
            v-on:keyup="enterGetPeople" 
            name="searchName"
        >
        <button 
        @click="getPeople"
        class="btn btn-outline-secondary" 
        type="button" >검색</button>
    </div>
    <div class="row">
        <div class="col-md-6">
        <ul class="list-group">
            <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(p, index) in people"
            :key="index"
            @click="setPersonActive(index)"
            @mouseover="setPersonHover(index)"
            @mouseleave="resetPersonHover()"
            >
            {{ p.name }}
            </li>
        </ul>
        </div>
        <div class="col-md-6">
            <div v-if="hoverIndex>-1">
                <div><label><strong>이름:</strong></label> {{ people[hoverIndex].name }}</div>
                <div><label><strong>성별:</strong></label> {{ people[hoverIndex].sex }}</div>
                <div><label><strong>생년월일:</strong></label> {{ people[hoverIndex].dob }}</div>
            </div>
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
      hoverIndex : -1
    };
  },
  methods: {
    enterGetPeople: function(e) {
      if (e.keyCode === 13 && this.searchName) {
          this.currentIndex = -1;
          personDS.getPeopleByName(this.searchName)
                .then(res => {
                    this.people = res.data;
                })
                .catch(e => {
                    console.log(e);
                })
      }
    },
    getPeople() {
        if(this.searchName) {
            this.currentIndex = -1;
             personDS.getPeopleByName(this.searchName)
                .then(res => {
                    this.people = res.data;
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    setPersonActive(index) {
        this.currentIndex = index;
        this.$emit('select', this.people[this.currentIndex]);
    },
    setPersonHover(index) {
      this.hoverIndex = index;
    },
    resetPersonHover() {
      this.hoverIndex = -1;
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>