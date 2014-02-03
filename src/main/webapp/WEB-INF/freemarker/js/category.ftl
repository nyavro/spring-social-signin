<script type="text/javascript">
    function CategoryController($scope, $http, searchService) {
        $http.get('/ajax/category/list').success(
            function(data, status) {
                $scope.categories = data;
            }
        )
        .error(function(data, status) {
            // Some error occurred
        });

        $scope.addCategoryFilter = function(category) {
            searchService.addCategoryFilter(category);
        }
    }
</script>