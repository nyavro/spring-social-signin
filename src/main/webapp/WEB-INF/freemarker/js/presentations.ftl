<script type="text/javascript">
    function PresentationListController($scope, $http, searchService) {
        searchService.subscribe(
            function(presenations) {
                $scope.presentations = presenations;
            }
        );
//        $scope.presentations = searchService.presentations;
//        $scope.category = searchService.category;
        $scope.formatDate = function(timestamp) {
            return moment(timestamp).format("DD.MM.YYYY HH:mm");
        }
    };
</script>