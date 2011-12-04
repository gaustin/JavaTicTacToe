module TicTacToe
  module UserMessaging
    def set_error(message)
      session[:error_statement] = message
    end

    def clear_error
      set_error nil
    end

    def set_message(message)
      session[:message] = message
    end

    def clear_message
      set_message nil
    end
  end
end
