<!DOCTYPE html>
<html>
<body>
    <h2>Client Registration Form</h2>
    <p>Dear Valued Client,</p>

    <p>To complete your registration, please follow these steps:</p>

    <ol>
        <li>Go to: <a href="${submitUrl}">${submitUrl}</a></li>
        <li>Complete and submit the client registration form</li>
    </ol>

    <p>Default Billing Rate: ${defaultBillingRate}</p>

    <#-- Add a null check -->
    <#if defaultBillingRate??>
        Default Billing Rate: ${defaultBillingRate}
    <#else>
        Default Billing Rate: Not specified
    </#if>

    <p>This link will expire shortly. Please complete your registration as soon as possible.</p>

    <p>Best regards,<br>
    KAMJRITZTEX IT Solutions Pvt Ltd.</p>
</body>
</html>