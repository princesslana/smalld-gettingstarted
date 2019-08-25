require_relative 'smalld-gettingstarted-ruby_jars'

java_import com.github.princesslana.smalld.SmallD

require 'json'

def is_message_create?(json)
  json['op'] == 0 && json['t'] == 'MESSAGE_CREATE'
end

def message_content(json)
  json['d']['content']
end

SmallD.run(ENV['SMALLD_TOKEN']) do |smalld|
  smalld.onGatewayPayload do |p_str|
    p_json = JSON.parse p_str

    if is_message_create?(p_json) && message_content(p_json) == '++ping'
      channel_id = p_json['d']['channel_id']

      smalld.post "/channels/#{channel_id}/messages", { content: 'pong' }.to_json 
    end
  end
end
