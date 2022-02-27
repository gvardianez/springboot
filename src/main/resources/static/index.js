angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.deleteProduct = function (productId) {
            $http.delete(contextPath + '/products/' + productId)
                .then(function (response) {
                    $scope.loadProducts();
                });
    }

    $scope.changeScore = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                productId: productId,
                deltaCost: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }
    $scope.loadProducts();

});