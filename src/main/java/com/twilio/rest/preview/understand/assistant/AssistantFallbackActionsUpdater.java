/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.understand.assistant;

import com.twilio.base.Updater;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.Map;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class AssistantFallbackActionsUpdater extends Updater<AssistantFallbackActions> {
    private final String pathAssistantSid;
    private Map<String, Object> fallbackActions;

    /**
     * Construct a new AssistantFallbackActionsUpdater.
     * 
     * @param pathAssistantSid The assistant_sid
     */
    public AssistantFallbackActionsUpdater(final String pathAssistantSid) {
        this.pathAssistantSid = pathAssistantSid;
    }

    /**
     * The fallback_actions.
     * 
     * @param fallbackActions The fallback_actions
     * @return this
     */
    public AssistantFallbackActionsUpdater setFallbackActions(final Map<String, Object> fallbackActions) {
        this.fallbackActions = fallbackActions;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated AssistantFallbackActions
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public AssistantFallbackActions update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/understand/Assistants/" + this.pathAssistantSid + "/FallbackActions",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("AssistantFallbackActions update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return AssistantFallbackActions.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (fallbackActions != null) {
            request.addPostParam("FallbackActions", Converter.mapToJson(fallbackActions));
        }
    }
}