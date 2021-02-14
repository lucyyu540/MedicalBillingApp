<template>
    <div>
        <name-search v-on:select="selectPerson"></name-search>
        <ul class="list-group padding " v-if="receipts.length>0" >
            <h5>수납 내역</h5>
            <div class = "row">
                    <div class = "col-1">id</div>
                    <div class = "col">날짜</div>
                    <div class = "col">총진료비</div>
                    <div class = "col">환자부담금</div>
                    <div class = "col">누적 수납</div>
                    <div class = "col">수납</div>
                    <div class = "col">환불</div>
            </div>
           <li class="list-group-item"
                :class="{ active: index == currentIndex }"
                v-for="(r, index) in receipts"
                :key="index"
                @click="setReceiptActive(index)"
            >
                <div class = "row">
                    <div class = "col-1"><label><small>{{ r.id }}</small></label></div>
                    <div class = "col">{{ r.date }}</div>
                    <div class = "col">₩{{ r.invoice.total }}</div>
                    <div class = "col">₩{{ r.invoice.outOfPocket }}</div>
                    <div class = "col">₩{{ r.invoice.paid }}</div>
                    <div class = "col">₩{{r.amount }}</div>
                    <div class = "col">₩{{r.refunded }}</div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import receiptDS from '../service/ReceiptDataService';
import SearchPerson from "./SearchPerson"
export default {
    components: {
      'name-search' : SearchPerson
    },
  name: "invoice-search",
  data() {
    return {
      receipts: [],
      currentIndex: -1,
      person: null
    };
  },
  methods: {
    selectPerson(p) {
        this.person = p;
        if(this.person) {
            this.currentIndex = -1;
            receiptDS.getAllReceiptsByPid(this.person.id)
                .then(res => {
                    this.receipts =res.data
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    setReceiptActive(index) {
        this.currentIndex = index;
        this.$emit('select', this.receipts[this.currentIndex]);
    }
  }
};
</script>

<style>
.padding{
  padding-top: 50px;
}
</style>
