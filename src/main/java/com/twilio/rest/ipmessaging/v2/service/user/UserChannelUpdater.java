/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service.user;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class UserChannelUpdater extends Updater<UserChannel> {
    private final String pathServiceSid;
    private final String pathUserSid;
    private final String pathChannelSid;
    private final UserChannel.NotificationLevel notificationLevel;

    /**
     * Construct a new UserChannelUpdater.
     * 
     * @param pathServiceSid The SID of the Service to update the resource from
     * @param pathUserSid The SID of the User to update the User Channel resource
     *                    from
     * @param pathChannelSid The SID of the Channel with the User Channel resource
     *                       to update
     * @param notificationLevel The push notification level to assign to the User
     *                          Channel
     */
    public UserChannelUpdater(final String pathServiceSid, 
                              final String pathUserSid, 
                              final String pathChannelSid, 
                              final UserChannel.NotificationLevel notificationLevel) {
        this.pathServiceSid = pathServiceSid;
        this.pathUserSid = pathUserSid;
        this.pathChannelSid = pathChannelSid;
        this.notificationLevel = notificationLevel;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated UserChannel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public UserChannel update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Users/" + this.pathUserSid + "/Channels/" + this.pathChannelSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UserChannel update failed: Unable to connect to server");
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

        return UserChannel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (notificationLevel != null) {
            request.addPostParam("NotificationLevel", notificationLevel.toString());
        }
    }
}