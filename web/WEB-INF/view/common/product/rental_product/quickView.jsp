<div class="modal fade quickview-modal" id="quickview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <span id="quickViewLoaderGif" class="inner-load "></span>
      <div class="modal-body" id="developerPartialRenderView">
      </div>
    </div>
  </div>
</div>

<script>
  function viewProductDetails(productId){
    $("#developerPartialRenderView").html("");
    $("#quickViewLoaderGif").show();
    $.ajax({
      type: "GET",
      url: BASEURL+"/product/product-details/partial-load/"+productId,
      success: function(data){
        $("#quickViewLoaderGif").hide();
        $("#developerPartialRenderView").html(data);
      },error:function(){
        $("#quickViewLoaderGif").hide();
      }
    });
  }
</script>