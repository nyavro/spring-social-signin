<#macro service>
    <script type="text/javascript">
        var search = {};
        app.factory('searchService', function ($http) {
            var presentations = [];
            $http({url: '/presentation/list', method: "GET"})
                .success(
                    function (data, status, headers, config) {
                        presentations = data;
                        search.callback(presentations);
                    }
                );
//            var presentations = $.ajax({
//                    type: "GET",
//                    url: '/presentation/list',
//                    async: false
//                });
            return {
                subscribe: function (callback) {
                    search.callback = callback;
                },
                addCategoryFilter: function(category) {
                    $http({url: '/presentation/search/' + category, method: "GET"})
                        .success(
                            function (data, status, headers, config) {
                                presentations = data;
                                search.callback(presentations);
                            }
                        );
                }
            }
        });
    </script>
</#macro>