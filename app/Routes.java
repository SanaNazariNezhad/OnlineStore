public class Routes{

	@SerializedName("/wc/v3/products/attributes/(?P<attribute_id>[\d]+)/terms/(?P<id>[\d]+)")
	private WcV3ProductsAttributesPAttributeIdDTermsPIdD wcV3ProductsAttributesPAttributeIdDTermsPIdD;

	@SerializedName("/wc/v3/products/categories/batch")
	private WcV3ProductsCategoriesBatch wcV3ProductsCategoriesBatch;

	@SerializedName("/wc/v3/customers")
	private WcV3Customers wcV3Customers;

	@SerializedName("/wc/v3/shipping/zones/(?P<id>[\d]+)/locations")
	private WcV3ShippingZonesPIdDLocations wcV3ShippingZonesPIdDLocations;

	@SerializedName("/wc/v3/products/tags")
	private WcV3ProductsTags wcV3ProductsTags;

	@SerializedName("/wc/v3/customers/(?P<customer_id>[\d]+)/downloads")
	private WcV3CustomersPCustomerIdDDownloads wcV3CustomersPCustomerIdDDownloads;

	@SerializedName("/wc/v3/taxes")
	private WcV3Taxes wcV3Taxes;

	@SerializedName("/wc/v3/customers/(?P<id>[\d]+)")
	private WcV3CustomersPIdD wcV3CustomersPIdD;

	@SerializedName("/wc/v3/settings/(?P<group_id>[\w-]+)")
	private WcV3SettingsPGroupIdW wcV3SettingsPGroupIdW;

	@SerializedName("/wc/v3/products")
	private WcV3Products wcV3Products;

	@SerializedName("/wc/v3/payment_gateways/(?P<id>[\w-]+)")
	private WcV3PaymentGatewaysPIdW wcV3PaymentGatewaysPIdW;

	@SerializedName("/wc/v3/products/attributes/(?P<id>[\d]+)")
	private WcV3ProductsAttributesPIdD wcV3ProductsAttributesPIdD;

	@SerializedName("/wc/v3/reports/products/totals")
	private WcV3ReportsProductsTotals wcV3ReportsProductsTotals;

	@SerializedName("/wc/v3/settings/(?P<group_id>[\w-]+)/batch")
	private WcV3SettingsPGroupIdWBatch wcV3SettingsPGroupIdWBatch;

	@SerializedName("/wc/v3/system_status/tools")
	private WcV3SystemStatusTools wcV3SystemStatusTools;

	@SerializedName("/wc/v3/customers/batch")
	private WcV3CustomersBatch wcV3CustomersBatch;

	@SerializedName("/wc/v3/products/categories/(?P<id>[\d]+)")
	private WcV3ProductsCategoriesPIdD wcV3ProductsCategoriesPIdD;

	@SerializedName("/wc/v3/system_status/tools/(?P<id>[\w-]+)")
	private WcV3SystemStatusToolsPIdW wcV3SystemStatusToolsPIdW;

	@SerializedName("/wc/v3/shipping_methods")
	private WcV3ShippingMethods wcV3ShippingMethods;

	@SerializedName("/wc/v3/orders/(?P<order_id>[\d]+)/notes/(?P<id>[\d]+)")
	private WcV3OrdersPOrderIdDNotesPIdD wcV3OrdersPOrderIdDNotesPIdD;

	@SerializedName("/wc/v3/taxes/(?P<id>[\d]+)")
	private WcV3TaxesPIdD wcV3TaxesPIdD;

	@SerializedName("/wc/v3/data/countries")
	private WcV3DataCountries wcV3DataCountries;

	@SerializedName("/wc/v3/products/shipping_classes/batch")
	private WcV3ProductsShippingClassesBatch wcV3ProductsShippingClassesBatch;

	@SerializedName("/wc/v3/webhooks/(?P<id>[\d]+)")
	private WcV3WebhooksPIdD wcV3WebhooksPIdD;

	@SerializedName("/wc/v3/shipping/zones/(?P<id>[\d]+)")
	private WcV3ShippingZonesPIdD wcV3ShippingZonesPIdD;

	@SerializedName("/wc/v3/settings/batch")
	private WcV3SettingsBatch wcV3SettingsBatch;

	@SerializedName("/wc/v3/webhooks")
	private WcV3Webhooks wcV3Webhooks;

	@SerializedName("/wc/v3/products/tags/batch")
	private WcV3ProductsTagsBatch wcV3ProductsTagsBatch;

	@SerializedName("/wc/v3/shipping/zones/(?P<zone_id>[\d]+)/methods")
	private WcV3ShippingZonesPZoneIdDMethods wcV3ShippingZonesPZoneIdDMethods;

	@SerializedName("/wc/v3/reports/top_sellers")
	private WcV3ReportsTopSellers wcV3ReportsTopSellers;

	@SerializedName("/wc/v3/shipping_methods/(?P<id>[\w-]+)")
	private WcV3ShippingMethodsPIdW wcV3ShippingMethodsPIdW;

	@SerializedName("/wc/v3/products/reviews")
	private WcV3ProductsReviews wcV3ProductsReviews;

	@SerializedName("/wc/v3/shipping/zones/(?P<zone_id>[\d]+)/methods/(?P<instance_id>[\d]+)")
	private WcV3ShippingZonesPZoneIdDMethodsPInstanceIdD wcV3ShippingZonesPZoneIdDMethodsPInstanceIdD;

	@SerializedName("/wc/v3/orders/(?P<order_id>[\d]+)/notes")
	private WcV3OrdersPOrderIdDNotes wcV3OrdersPOrderIdDNotes;

	@SerializedName("/wc/v3/shipping/zones")
	private WcV3ShippingZones wcV3ShippingZones;

	@SerializedName("/wc/v3/products/shipping_classes/(?P<id>[\d]+)")
	private WcV3ProductsShippingClassesPIdD wcV3ProductsShippingClassesPIdD;

	@SerializedName("/wc/v3/orders/(?P<order_id>[\d]+)/refunds")
	private WcV3OrdersPOrderIdDRefunds wcV3OrdersPOrderIdDRefunds;

	@SerializedName("/wc/v3")
	private WcV3 wcV3;

	@SerializedName("/wc/v3/settings/(?P<group_id>[\w-]+)/(?P<id>[\w-]+)")
	private WcV3SettingsPGroupIdWPIdW wcV3SettingsPGroupIdWPIdW;

	@SerializedName("/wc/v3/orders/(?P<id>[\d]+)")
	private WcV3OrdersPIdD wcV3OrdersPIdD;

	@SerializedName("/wc/v3/products/shipping_classes")
	private WcV3ProductsShippingClasses wcV3ProductsShippingClasses;

	@SerializedName("/wc/v3/reports/reviews/totals")
	private WcV3ReportsReviewsTotals wcV3ReportsReviewsTotals;

	@SerializedName("/wc/v3/products/(?P<product_id>[\d]+)/variations")
	private WcV3ProductsPProductIdDVariations wcV3ProductsPProductIdDVariations;

	@SerializedName("/wc/v3/reports/orders/totals")
	private WcV3ReportsOrdersTotals wcV3ReportsOrdersTotals;

	@SerializedName("/wc/v3/data/currencies/(?P<currency>[\w-]{3})")
	private WcV3DataCurrenciesPCurrencyW3 wcV3DataCurrenciesPCurrencyW3;

	@SerializedName("/wc/v3/products/reviews/(?P<id>[\d]+)")
	private WcV3ProductsReviewsPIdD wcV3ProductsReviewsPIdD;

	@SerializedName("/wc/v3/products/attributes")
	private WcV3ProductsAttributes wcV3ProductsAttributes;

	@SerializedName("/wc/v3/products/attributes/(?P<attribute_id>[\d]+)/terms/batch")
	private WcV3ProductsAttributesPAttributeIdDTermsBatch wcV3ProductsAttributesPAttributeIdDTermsBatch;

	@SerializedName("/wc/v3/products/tags/(?P<id>[\d]+)")
	private WcV3ProductsTagsPIdD wcV3ProductsTagsPIdD;

	@SerializedName("/wc/v3/coupons/batch")
	private WcV3CouponsBatch wcV3CouponsBatch;

	@SerializedName("/wc/v3/payment_gateways")
	private WcV3PaymentGateways wcV3PaymentGateways;

	@SerializedName("/wc/v3/reports/sales")
	private WcV3ReportsSales wcV3ReportsSales;

	@SerializedName("/wc/v3/data/continents")
	private WcV3DataContinents wcV3DataContinents;

	@SerializedName("/wc/v3/taxes/classes/(?P<slug>\w[\w\s\-]*)")
	private WcV3TaxesClassesPSlugWWS wcV3TaxesClassesPSlugWWS;

	@SerializedName("/wc/v3/products/(?P<id>[\d]+)")
	private WcV3ProductsPIdD wcV3ProductsPIdD;

	@SerializedName("/wc/v3/reports")
	private WcV3Reports wcV3Reports;

	@SerializedName("/wc/v3/orders/(?P<order_id>[\d]+)/refunds/(?P<id>[\d]+)")
	private WcV3OrdersPOrderIdDRefundsPIdD wcV3OrdersPOrderIdDRefundsPIdD;

	@SerializedName("/wc/v3/products/(?P<product_id>[\d]+)/variations/batch")
	private WcV3ProductsPProductIdDVariationsBatch wcV3ProductsPProductIdDVariationsBatch;

	@SerializedName("/wc/v3/reports/coupons/totals")
	private WcV3ReportsCouponsTotals wcV3ReportsCouponsTotals;

	@SerializedName("/wc/v3/system_status")
	private WcV3SystemStatus wcV3SystemStatus;

	@SerializedName("/wc/v3/data/countries/(?P<location>[\w-]+)")
	private WcV3DataCountriesPLocationW wcV3DataCountriesPLocationW;

	@SerializedName("/wc/v3/settings")
	private WcV3Settings wcV3Settings;

	@SerializedName("/wc/v3/coupons/(?P<id>[\d]+)")
	private WcV3CouponsPIdD wcV3CouponsPIdD;

	@SerializedName("/wc/v3/reports/customers/totals")
	private WcV3ReportsCustomersTotals wcV3ReportsCustomersTotals;

	@SerializedName("/wc/v3/data/currencies")
	private WcV3DataCurrencies wcV3DataCurrencies;

	@SerializedName("/wc/v3/products/categories")
	private WcV3ProductsCategories wcV3ProductsCategories;

	@SerializedName("/wc/v3/products/reviews/batch")
	private WcV3ProductsReviewsBatch wcV3ProductsReviewsBatch;

	@SerializedName("/wc/v3/webhooks/batch")
	private WcV3WebhooksBatch wcV3WebhooksBatch;

	@SerializedName("/wc/v3/products/batch")
	private WcV3ProductsBatch wcV3ProductsBatch;

	@SerializedName("/wc/v3/taxes/classes")
	private WcV3TaxesClasses wcV3TaxesClasses;

	@SerializedName("/wc/v3/data")
	private WcV3Data wcV3Data;

	@SerializedName("/wc/v3/data/continents/(?P<location>[\w-]+)")
	private WcV3DataContinentsPLocationW wcV3DataContinentsPLocationW;

	@SerializedName("/wc/v3/taxes/batch")
	private WcV3TaxesBatch wcV3TaxesBatch;

	@SerializedName("/wc/v3/coupons")
	private WcV3Coupons wcV3Coupons;

	@SerializedName("/wc/v3/orders/batch")
	private WcV3OrdersBatch wcV3OrdersBatch;

	@SerializedName("/wc/v3/data/currencies/current")
	private WcV3DataCurrenciesCurrent wcV3DataCurrenciesCurrent;

	@SerializedName("/wc/v3/orders")
	private WcV3Orders wcV3Orders;

	@SerializedName("/wc/v3/products/(?P<product_id>[\d]+)/variations/(?P<id>[\d]+)")
	private WcV3ProductsPProductIdDVariationsPIdD wcV3ProductsPProductIdDVariationsPIdD;

	@SerializedName("/wc/v3/products/attributes/batch")
	private WcV3ProductsAttributesBatch wcV3ProductsAttributesBatch;

	@SerializedName("/wc/v3/products/attributes/(?P<attribute_id>[\d]+)/terms")
	private WcV3ProductsAttributesPAttributeIdDTerms wcV3ProductsAttributesPAttributeIdDTerms;

	public WcV3ProductsAttributesPAttributeIdDTermsPIdD getWcV3ProductsAttributesPAttributeIdDTermsPIdD(){
		return wcV3ProductsAttributesPAttributeIdDTermsPIdD;
	}

	public WcV3ProductsCategoriesBatch getWcV3ProductsCategoriesBatch(){
		return wcV3ProductsCategoriesBatch;
	}

	public WcV3Customers getWcV3Customers(){
		return wcV3Customers;
	}

	public WcV3ShippingZonesPIdDLocations getWcV3ShippingZonesPIdDLocations(){
		return wcV3ShippingZonesPIdDLocations;
	}

	public WcV3ProductsTags getWcV3ProductsTags(){
		return wcV3ProductsTags;
	}

	public WcV3CustomersPCustomerIdDDownloads getWcV3CustomersPCustomerIdDDownloads(){
		return wcV3CustomersPCustomerIdDDownloads;
	}

	public WcV3Taxes getWcV3Taxes(){
		return wcV3Taxes;
	}

	public WcV3CustomersPIdD getWcV3CustomersPIdD(){
		return wcV3CustomersPIdD;
	}

	public WcV3SettingsPGroupIdW getWcV3SettingsPGroupIdW(){
		return wcV3SettingsPGroupIdW;
	}

	public WcV3Products getWcV3Products(){
		return wcV3Products;
	}

	public WcV3PaymentGatewaysPIdW getWcV3PaymentGatewaysPIdW(){
		return wcV3PaymentGatewaysPIdW;
	}

	public WcV3ProductsAttributesPIdD getWcV3ProductsAttributesPIdD(){
		return wcV3ProductsAttributesPIdD;
	}

	public WcV3ReportsProductsTotals getWcV3ReportsProductsTotals(){
		return wcV3ReportsProductsTotals;
	}

	public WcV3SettingsPGroupIdWBatch getWcV3SettingsPGroupIdWBatch(){
		return wcV3SettingsPGroupIdWBatch;
	}

	public WcV3SystemStatusTools getWcV3SystemStatusTools(){
		return wcV3SystemStatusTools;
	}

	public WcV3CustomersBatch getWcV3CustomersBatch(){
		return wcV3CustomersBatch;
	}

	public WcV3ProductsCategoriesPIdD getWcV3ProductsCategoriesPIdD(){
		return wcV3ProductsCategoriesPIdD;
	}

	public WcV3SystemStatusToolsPIdW getWcV3SystemStatusToolsPIdW(){
		return wcV3SystemStatusToolsPIdW;
	}

	public WcV3ShippingMethods getWcV3ShippingMethods(){
		return wcV3ShippingMethods;
	}

	public WcV3OrdersPOrderIdDNotesPIdD getWcV3OrdersPOrderIdDNotesPIdD(){
		return wcV3OrdersPOrderIdDNotesPIdD;
	}

	public WcV3TaxesPIdD getWcV3TaxesPIdD(){
		return wcV3TaxesPIdD;
	}

	public WcV3DataCountries getWcV3DataCountries(){
		return wcV3DataCountries;
	}

	public WcV3ProductsShippingClassesBatch getWcV3ProductsShippingClassesBatch(){
		return wcV3ProductsShippingClassesBatch;
	}

	public WcV3WebhooksPIdD getWcV3WebhooksPIdD(){
		return wcV3WebhooksPIdD;
	}

	public WcV3ShippingZonesPIdD getWcV3ShippingZonesPIdD(){
		return wcV3ShippingZonesPIdD;
	}

	public WcV3SettingsBatch getWcV3SettingsBatch(){
		return wcV3SettingsBatch;
	}

	public WcV3Webhooks getWcV3Webhooks(){
		return wcV3Webhooks;
	}

	public WcV3ProductsTagsBatch getWcV3ProductsTagsBatch(){
		return wcV3ProductsTagsBatch;
	}

	public WcV3ShippingZonesPZoneIdDMethods getWcV3ShippingZonesPZoneIdDMethods(){
		return wcV3ShippingZonesPZoneIdDMethods;
	}

	public WcV3ReportsTopSellers getWcV3ReportsTopSellers(){
		return wcV3ReportsTopSellers;
	}

	public WcV3ShippingMethodsPIdW getWcV3ShippingMethodsPIdW(){
		return wcV3ShippingMethodsPIdW;
	}

	public WcV3ProductsReviews getWcV3ProductsReviews(){
		return wcV3ProductsReviews;
	}

	public WcV3ShippingZonesPZoneIdDMethodsPInstanceIdD getWcV3ShippingZonesPZoneIdDMethodsPInstanceIdD(){
		return wcV3ShippingZonesPZoneIdDMethodsPInstanceIdD;
	}

	public WcV3OrdersPOrderIdDNotes getWcV3OrdersPOrderIdDNotes(){
		return wcV3OrdersPOrderIdDNotes;
	}

	public WcV3ShippingZones getWcV3ShippingZones(){
		return wcV3ShippingZones;
	}

	public WcV3ProductsShippingClassesPIdD getWcV3ProductsShippingClassesPIdD(){
		return wcV3ProductsShippingClassesPIdD;
	}

	public WcV3OrdersPOrderIdDRefunds getWcV3OrdersPOrderIdDRefunds(){
		return wcV3OrdersPOrderIdDRefunds;
	}

	public WcV3 getWcV3(){
		return wcV3;
	}

	public WcV3SettingsPGroupIdWPIdW getWcV3SettingsPGroupIdWPIdW(){
		return wcV3SettingsPGroupIdWPIdW;
	}

	public WcV3OrdersPIdD getWcV3OrdersPIdD(){
		return wcV3OrdersPIdD;
	}

	public WcV3ProductsShippingClasses getWcV3ProductsShippingClasses(){
		return wcV3ProductsShippingClasses;
	}

	public WcV3ReportsReviewsTotals getWcV3ReportsReviewsTotals(){
		return wcV3ReportsReviewsTotals;
	}

	public WcV3ProductsPProductIdDVariations getWcV3ProductsPProductIdDVariations(){
		return wcV3ProductsPProductIdDVariations;
	}

	public WcV3ReportsOrdersTotals getWcV3ReportsOrdersTotals(){
		return wcV3ReportsOrdersTotals;
	}

	public WcV3DataCurrenciesPCurrencyW3 getWcV3DataCurrenciesPCurrencyW3(){
		return wcV3DataCurrenciesPCurrencyW3;
	}

	public WcV3ProductsReviewsPIdD getWcV3ProductsReviewsPIdD(){
		return wcV3ProductsReviewsPIdD;
	}

	public WcV3ProductsAttributes getWcV3ProductsAttributes(){
		return wcV3ProductsAttributes;
	}

	public WcV3ProductsAttributesPAttributeIdDTermsBatch getWcV3ProductsAttributesPAttributeIdDTermsBatch(){
		return wcV3ProductsAttributesPAttributeIdDTermsBatch;
	}

	public WcV3ProductsTagsPIdD getWcV3ProductsTagsPIdD(){
		return wcV3ProductsTagsPIdD;
	}

	public WcV3CouponsBatch getWcV3CouponsBatch(){
		return wcV3CouponsBatch;
	}

	public WcV3PaymentGateways getWcV3PaymentGateways(){
		return wcV3PaymentGateways;
	}

	public WcV3ReportsSales getWcV3ReportsSales(){
		return wcV3ReportsSales;
	}

	public WcV3DataContinents getWcV3DataContinents(){
		return wcV3DataContinents;
	}

	public WcV3TaxesClassesPSlugWWS getWcV3TaxesClassesPSlugWWS(){
		return wcV3TaxesClassesPSlugWWS;
	}

	public WcV3ProductsPIdD getWcV3ProductsPIdD(){
		return wcV3ProductsPIdD;
	}

	public WcV3Reports getWcV3Reports(){
		return wcV3Reports;
	}

	public WcV3OrdersPOrderIdDRefundsPIdD getWcV3OrdersPOrderIdDRefundsPIdD(){
		return wcV3OrdersPOrderIdDRefundsPIdD;
	}

	public WcV3ProductsPProductIdDVariationsBatch getWcV3ProductsPProductIdDVariationsBatch(){
		return wcV3ProductsPProductIdDVariationsBatch;
	}

	public WcV3ReportsCouponsTotals getWcV3ReportsCouponsTotals(){
		return wcV3ReportsCouponsTotals;
	}

	public WcV3SystemStatus getWcV3SystemStatus(){
		return wcV3SystemStatus;
	}

	public WcV3DataCountriesPLocationW getWcV3DataCountriesPLocationW(){
		return wcV3DataCountriesPLocationW;
	}

	public WcV3Settings getWcV3Settings(){
		return wcV3Settings;
	}

	public WcV3CouponsPIdD getWcV3CouponsPIdD(){
		return wcV3CouponsPIdD;
	}

	public WcV3ReportsCustomersTotals getWcV3ReportsCustomersTotals(){
		return wcV3ReportsCustomersTotals;
	}

	public WcV3DataCurrencies getWcV3DataCurrencies(){
		return wcV3DataCurrencies;
	}

	public WcV3ProductsCategories getWcV3ProductsCategories(){
		return wcV3ProductsCategories;
	}

	public WcV3ProductsReviewsBatch getWcV3ProductsReviewsBatch(){
		return wcV3ProductsReviewsBatch;
	}

	public WcV3WebhooksBatch getWcV3WebhooksBatch(){
		return wcV3WebhooksBatch;
	}

	public WcV3ProductsBatch getWcV3ProductsBatch(){
		return wcV3ProductsBatch;
	}

	public WcV3TaxesClasses getWcV3TaxesClasses(){
		return wcV3TaxesClasses;
	}

	public WcV3Data getWcV3Data(){
		return wcV3Data;
	}

	public WcV3DataContinentsPLocationW getWcV3DataContinentsPLocationW(){
		return wcV3DataContinentsPLocationW;
	}

	public WcV3TaxesBatch getWcV3TaxesBatch(){
		return wcV3TaxesBatch;
	}

	public WcV3Coupons getWcV3Coupons(){
		return wcV3Coupons;
	}

	public WcV3OrdersBatch getWcV3OrdersBatch(){
		return wcV3OrdersBatch;
	}

	public WcV3DataCurrenciesCurrent getWcV3DataCurrenciesCurrent(){
		return wcV3DataCurrenciesCurrent;
	}

	public WcV3Orders getWcV3Orders(){
		return wcV3Orders;
	}

	public WcV3ProductsPProductIdDVariationsPIdD getWcV3ProductsPProductIdDVariationsPIdD(){
		return wcV3ProductsPProductIdDVariationsPIdD;
	}

	public WcV3ProductsAttributesBatch getWcV3ProductsAttributesBatch(){
		return wcV3ProductsAttributesBatch;
	}

	public WcV3ProductsAttributesPAttributeIdDTerms getWcV3ProductsAttributesPAttributeIdDTerms(){
		return wcV3ProductsAttributesPAttributeIdDTerms;
	}
}
