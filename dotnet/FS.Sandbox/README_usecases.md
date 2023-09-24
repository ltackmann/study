PaymentFileRecived
 * PaymentFileParsed
    * PaymentSpecCreated
 * PaymentsDistributed
    * PlacePaymentOnPersons
        * PlacePaymentOnPerson
            * PlacePaymentOnPersonsPolicies
                * PlacePaymentOnPolicy

RegisterPerson
 - Validations
    * CheckPersonInfo

RegisterCompany
 - Validations
    * CheckCompanyInfo

CreateAgreement

CreateCompanyAgreement

CreatePolicy
 * CreatePolicyOfProduct
 * AssociatePersonWithPolicy

PersonDeathNotice
 * LocatePoliciesImpactedByDeathEvent
    * LocatePolicesOwnedByPerson
        * TriggerDeathEventOnPolicies

PersonRetiredNotice
* LocatePolicesOwnedByPerson
    * TriggerRetiredEventOnPolicies

