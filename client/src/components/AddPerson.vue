<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">이름</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="person.name"
          name="name"
        />
      </div>
    <div class="form-group">
      <label for="sex">성별</label>
      <select class="form-control" 
      id="sex"
      required
      v-model="person.sex"
      name="sex"
      >
        <option value="F">여</option>
        <option value="M">남</option>
    </select>
     </div>
      <div class="form-group">
        <label for="dob">생년월일</label>
        <input
          class="form-control"
          type="date"
          id="dob"
          required
          v-model="person.dob"
          name="dob"
        />
      </div>

      <b-button  block variant="primary"
        :class="{disabled: person.name==null||person.sex==null||person.dob==null}"
        @click="savePerson"
        >등록</b-button>
    </div>

    <div v-else>
      <h4>등록을 완료했습니다</h4>
      <b-button  block variant="primary"
        @click="newPerson"
        >추가 등록</b-button>
      
    </div>
  </div>
</template>

<script>
import personDS from "../service/PersonDataService";

export default {
  name: "person-add",
  data() {
    return {
      person: {
        name: null,
        sex: null,
        dob: new Date().toISOString().slice(0,10),
      },
      submitted: false,
    };
  },
  methods: {
    savePerson() {
      var data = {
        name: this.person.name,
        sex: this.person.sex,
        dob: this.person.dob
      };

      personDS.addPerson(JSON.parse(JSON.stringify(data)))
        .then(() => {
            console.log('saved');
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newPerson() {
      this.submitted = false;
      this.person = {
        name: null,
        sex: null,
        dob: new Date().toISOString().slice(0,10),
      };
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