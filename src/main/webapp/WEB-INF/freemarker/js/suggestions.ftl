<script type="text/javascript">
    function SuggestionsListController($scope, $http, searchService) {
        searchService.subscribe(
            function(suggestions) {
                $scope.suggestions = suggestions;
            }
        );
//        $scope.presentations = searchService.presentations;
//        $scope.category = searchService.category;
        $scope.formatDate = function(timestamp) {
            return moment(timestamp).format("DD.MM.YYYY HH:mm");
        }
    };
</script>