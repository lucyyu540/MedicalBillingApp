<template>
    <div>
        <name-search v-on:select="selectPerson"></name-search>
        <div class="submit-form">
            <div v-if="person">
            <div v-if="!submitted">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" 
                    type="radio" 
                    name="type" 
                    id="외래"
                    value = "외래"
                    v-model="invoice.type"
                    checked
                    >
                    <label class="form-check-label" for="외래">외래</label>
                </div>
                <div class="form-check form-check-inline">
                    <input 
                    class="form-check-input" 
                    type="radio" 
                    name="type" 
                    id="입원"
                    value = "입원"
                    v-model="invoice.type"
                    >
                    <label class="form-check-label" for="입원">입원</label>
                </div>
                <div class="form-group">
                    <label for="name">이름</label>
                    <input
                    type="text"
                    class="form-control"
                    id="name"
                    required
                    name="name"
                    :placeholder="person.name"
                    disabled
                    />
                </div>
                <div class="form-group">
                    <label for="department">진료과목</label>
                        <select class="form-control" 
                        id="department"
                        required
                        v-model="invoice.department"
                        name="department"
                        >
                            <option value="내분비내과">내분비내과</option>
                            <option value="외과">외과</option>
                        </select>
                </div>
                <div class="form-group">
                    <label for="total">총 진료비</label>
                    <input
                    type="number"
                    class="form-control"
                    id="total"
                    required
                    v-model="invoice.total"
                    name="name"
                    />
                </div>
                <button @click="saveInvoice" class="btn btn-success">등록</button>
            </div>
            <div v-else>
                <h4>등록 완료했습니다</h4>
                <button class="btn btn-success" @click="newInvoice">추가 등록</button>
            </div>
            </div>
        </div>
  </div>
</template>

<script>
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
        total: null,
        type: '외래',
        department: null
      },
      submitted: false,
      person: null
    };
  },
  methods: {
    selectPerson(p) {
        this.person = p;
    },
    saveInvoice() {
      var data = {
        pid: this.person.id,
        total: this.invoice.total,
        type: this.invoice.type,
        department: this.invoice.department
      };
      console.log(data);
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
      this.person = null;
      this.invoice = {
        pid: null,
        total: null,
        type: '외래',
        department: null
      }
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