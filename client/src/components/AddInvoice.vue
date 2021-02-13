<template>
    <div>
        <name-search></name-search>
        <div class="submit-form">
            <div v-if="!submitted">
                <div class="form-group">
                    <label for="type">진료과목</label>
                        <select class="form-control" 
                        id="type"
                        required
                        v-model="invoice.type"
                        name="type"
                        >
                            <option value="내분비내과">내분비내과</option>
                            <option value="외과">외과</option>
                        </select>
                </div>
                <button @click="saveInvoice" class="btn btn-success">등록</button>
            </div>
            <div v-else>
                <h4>등록 완료했습니다</h4>
                <button class="btn btn-success" @click="newInvoice">추가 등록</button>
            </div>
        </div>
  </div>
</template>

<script>
import personDS from "../service/PersonDataService";
import invoiceDS from '../service/InvoiceDataService';
import searchpersoncomp from "./SearchPerson"
export default {
    components: {
      'name-search' : searchpersoncomp
    },
  name: "invoice-add",
  data() {
    return {
      invoice: {
        pid: null,
        pName: null,
        total: null,
        type: null,
        department: null
      },
      submitted: false,
      name: null,
      people: [],
      currentIndex : -1,
      personFound : false,
      currentPerson: null,
      person: null
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
    saveInvoice() {
      var data = {
        pid: this.invoice.pid,
        pName: this.invoice.pName,
        total: this.invoice.total,
        type: this.invoice.type,
        department: this.invoice.department
      };

      invoiceDS.addInvoice(JSON.parse(JSON.stringify(data)))
        .then(() => {
            console.log('saved');
            this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newInvoice() {
      this.submitted = false;
      this.personFound = false;
      this.invoice = {};
      this.person = {};
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