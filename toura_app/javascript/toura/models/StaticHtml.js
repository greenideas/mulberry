dojo.provide('toura.models.StaticHtml');

dojo.require('toura.models._StorableAsset');

dojo.declare('toura.models.StaticHtml', [ toura.models._CaptionedAsset, toura.models._StorableAsset ], {
  constructor : function(store, item) {
    toura.log("I AM HERE")
    store.fetchItemByIdentity({
      identity : item.html._reference,
      onItem : function(item) {
        dojo.mixin(this, {
          id : store.getValue(item, 'id'),
          name : store.getValue(item, 'name')
        });
        this._getUrl(store, item);
      },
      scope : this
    });
  }
});
