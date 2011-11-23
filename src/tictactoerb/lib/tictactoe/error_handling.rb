module TicTacToe
  module ErrorHandling
    def set_error(message)
      session[:error_statement] = message
    end

    def clear_error
      set_error nil
    end
  end
end
