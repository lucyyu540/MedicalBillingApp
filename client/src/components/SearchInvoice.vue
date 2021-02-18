<template>
    <div>
        <name-search v-on:select="selectPerson"></name-search>
        <ul class="list-group padding">
            <div v-if="invoices.length>0" class = "row">
                    <div class = "col-1">id</div>
                    <div class = "col">진료과목</div>
                    <div class = "col">날짜</div>
                    <div class = "col"></div>
                    <div class = "col">총진료비</div>
                    <div class = "col">환자부담금</div>
                    <div class = "col">누적 수납</div>
            </div>
           <li class="list-group-item"
                :class="{ active: index == currentIndex }"
                v-for="(i, index) in invoices"
                :key="index"
                @click="setInvoiceActive(index)"
            >
                <div class = "row">
                    <div class = "col-1"><label><small>{{ i.id }}</small></label></div>
                    <div class = "col"><label><strong>{{i.department}}</strong></label></div>
                    <div class = "col">{{ i.date }}</div>
                    <div class = "col">{{ i.type }}</div>
                    <div class = "col">₩{{i.total }}</div>
                    <div class = "col">₩{{i.outOfPocket }}</div>
                    <div class = "col">₩{{i.paid }}</div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import invoiceDS from '../service/InvoiceDataService';
import SearchPerson from "./SearchPerson"
export default {
    components: {
      'name-search' : SearchPerson
    },
  name: "invoice-search",
  data() {
    return {
      invoices: [],
      currentIndex: -1,
      person: null
    };
  },
  methods: {
    selectPerson(p) {
        this.person = p;
        if(this.person) {
            this.currentIndex = -1;
            invoiceDS.getAllUnclearedInvoicesByPid(this.person.id)
                .then(res => {
                    this.invoices =res.data
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    setInvoiceActive(index) {
        this.currentIndex = index;
        this.$emit('select', this.invoices[this.currentIndex]);
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
.padding{
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>
