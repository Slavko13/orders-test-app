var updateProductsApi = Vue.resource('/updateProducts');
var orderApi = Vue.resource('/makeAnOrder')


Vue.component('order', {
        props: ['order'],
        data: function () {
            return {
            }
        },

    watch: {
        orderAttr: function (newVal, oldVal) {
            this.order = newVal;
        }},
        template:
            '<tr>'+
            '<td><input type="text" :id="order.uuid_name" :value="order.productName" disabled/></td>'+
            '<td><input type="text" :id="order.uuid_count" :value="order.count"/></td>'+
            '<input type="hidden" :id="order.uuid_serial" :value="order.serialNumber"/> '+
            '<td><button class="btn btn-danger" name="order.productName" :id="order.serialNumber" @click="deleteFromOrder()" >Удалить из заказ</button> </td>'+
            '</tr>',
    });

Vue.component('products-row', {
    props: ['productsItem','generatedComponents'],
    data: function() {
        return {
            generatedComponents: generatedComponents
        }
    },

    watch: {
        generatedComponentsAttr: function (newVal, oldVal) {
            this.generatedComponents = newVal;
        },

        productsItemAttr: function (newVal, oldVal) {
            this.productsItem = newVal;
        }},
    template:

        '<tr>'+
        '<td>{{productsItem.serialNumber}}</td>'+
        '<td>{{productsItem.productName}}</td>'+
        '<td>{{productsItem.serialProductionDate}}</td>'+
        '<td>{{productsItem.pricePerOne}}</td>'+
        '<td><button class="btn btn-success" name="productsItem.productName" :id="productsItem.serialNumber" @click="addToOrder(productsItem.productName,productsItem.pricePerOne, productsItem.serialNumber)" >Добавить в заказ</button> </td>'+
        '</tr>',

    methods: {
            addToOrder: function(name, price, serialNumber) {


                if (this.generatedComponents.length === 0) {
                    this.generatedComponents.push({
                        productName: name,
                        pricePerOne: price,
                        serialNumber: serialNumber,
                        count: 1,
                        uuid_name:  this.generatedComponents.length + 1 + '_details_name',
                        uuid_count: this.generatedComponents.length + 1 + '_details_count',
                        uuid_serial: this.generatedComponents.length + 1 +'_details_serial'
                    });
                    console.log(this.generatedComponents)

                }

                else {
                    for (let i = 0; i < this.generatedComponents.length ; i++) { // выведет 0, затем 1, затем 2
                        console.log(this.generatedComponents[i].serialNumber)
                        console.log(serialNumber)

                        if (this.generatedComponents[i].serialNumber === serialNumber) {
                            console.log('test')
                            this.generatedComponents[i].count += 1
                            console.log(this.generatedComponents[i].count)
                            return;

                        }
                    }
                            this.generatedComponents.push({
                                productName: name,
                                pricePerOne: price,
                                serialNumber: serialNumber,
                                count: 1,
                                uuid_name:  this.generatedComponents.length + 1 + '_details_name',
                                uuid_count: this.generatedComponents.length + 1 + '_details_count',
                                uuid_serial: this.generatedComponents.length + 1 +'_details_serial'
                            });

                }

            }
    }
});



Vue.component('products-list', {
    props: ['products', 'generatedComponents'],
    data: function() {
        return {
            products: products
        }
    },

    watch: {
        productsAttr: function (newVal, oldVal) {
            this.products = newVal;
        }
    },
    template:
        '<div class="container">' +
    '<div class="row">'+
    '<div class="col-md-6">'+
        '<button class="btn btn-danger" @click="updateProducts()">Обновить список </button>' +
        '<table class="table table-striped" >'+
        '<thead>'+
        '<tr>'+
        '<td ><b>Серийный номер товара</b></td>'+
        '<td ><b>Название товара</b></td>'+
        '<td><b>Дата серийного производства</b></td>'+
        '<td><b>Цена в рублях</b></td>'+
        '</tr>'+
        '</thead>'+
        '<tbody>'+
        '<products-row  :generatedComponents="generatedComponents" v-for="productsItem in products" :key="productsItem.id" :productsItem="productsItem" />'+
        '</tbody>'+
        '</table>'+
    '</div>'+
    '<button class="col-md-6">' +
        '<table class="table table-striped" >'+
        '<thead>'+
        '<tr>'+
        '<td ><b>Введите ваш адресс</b></td>'+
        '<td ><b>Введите ваше имя</b></td>'+

        '</tr>'+
        '</thead>'+
        '<tbody>'+
        '<td ><input type="text" name="address" v-model="clientAddress" /> </td>'+
        '<td ><input type="text" name="name" v-model="clientName" /> </td>'+
        '</tbody>'+
        '</table>'+


        '<table class="table table-striped" >'+
        '<thead>'+
        '<tr>'+
        '<td ><b>Название товара</b></td>'+
        '<td><b>Кол-во товара</b></td>'+
        '</tr>'+
        '</thead>'+
        '<tbody>'+
            '<order v-for="component in generatedComponents" :order="component"> </order>' +
        '</tbody>'+
        '</table>'+
        '<button class="btn btn-primary" @click="makeAnOrder()">Оформить заказ </button> '+
    '</div>' +
     '</div>'+
    '</div>',
    methods: {
        updateProducts: function () {
            updateProductsApi.save().then(result => {
                console.log(result)
                result.json().then(
                     data => {
                         for (let i = 0; i < data.length ; i++) { // выведет 0, затем 1, затем 2
                             this.products.push(data[i])
                         }

                     }
                 )
            })
        },
        makeAnOrder: function () {

            var orderDetails = []

            for (let i = 1; i < this.generatedComponents.length+1 ; i++) { // выведет 0, затем 1, затем 2
                var productName1 = document.getElementById(i + '_details_name').value;
                var serialNumber1 = document.getElementById(i + '_details_serial').value
                var count1 = document.getElementById(i + '_details_count').value

                orderDetails.push({
                    productName: productName1,
                    serialNumber: serialNumber1,
                    count: count1
                })
            }
                var order = {clientName: this.clientName, clientAddress: this.clientAddress, orderDetailsList: orderDetails}


                orderApi.save({}, order).then(result => {
                    result.json().then(
                        data => {

                            alert(data.clientName  + ' Вы оформили заказ №' + data.id + ' в ' + data.creationDate)
                            this.generatedComponents = []
                            console.log(this.generatedComponents)
                        }
                    )
                })
                console.log(orderDetails)
        }

    }


});




var app = new Vue({
    el: '#app',
    template: '<div>' +

        '<products-list :products="products" :generatedComponents="generatedComponents"/>' +

        '</div>',
    data: {
        products: frontendData.products,
        generatedComponents: []
    }
});