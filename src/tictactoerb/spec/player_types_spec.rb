require File.join(File.dirname(__FILE__), 'spec_helper')

describe PlayerTypes do
  it "should have a WebHuman constant" do
    PlayerTypes::WebHuman.should_not be_nil
  end
end

