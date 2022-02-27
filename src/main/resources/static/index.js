angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
        });
    };

    $scope.deleteProduct = function (productId) {
            $http.delete(contextPath + '/products/' + productId)
                .then(function (response) {
                    $scope.loadProducts();
                });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.createProduct)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeScore = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'Patch',
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